package com.workfusion.examples.aa_examples_bots.steam.task;

import com.workfusion.examples.aa_examples_bots.steam.automation.pages.GameDetailedInfoPage;
import com.workfusion.examples.aa_examples_bots.steam.orm.GameRepository;
import com.workfusion.examples.aa_examples_bots.steam.orm.GameRepositoryModule;
import com.workfusion.examples.aa_examples_bots.steam.orm.dto.Game;
import com.workfusion.examples.aa_examples_bots.steam.workflow.SteamDemoScenario;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Injector;
import com.workfusion.odf2.core.cdi.Requires;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.rpa.RpaDriver;
import com.workfusion.odf2.core.task.rpa.RpaFactory;
import com.workfusion.odf2.core.task.rpa.RpaRunner;
import org.slf4j.Logger;

import javax.inject.Inject;

@BotTask(requireRpa = true)
@Requires(GameRepositoryModule.class)
public class SteamDemoTask implements GenericTask {

    private final RpaRunner rpaRunner;
    private final Logger logger;
    private final GameRepository gameRepository;

    @Inject
    public SteamDemoTask(Injector injector) {
        RpaFactory rpaFactory = injector.instance(RpaFactory.class);
        this.rpaRunner = rpaFactory.builder(RpaDriver.UNIVERSAL).closeOnCompletion(true).build();
        this.logger = injector.instance(Logger.class);
        this.gameRepository = injector.instance(GameRepository.class);
    }

    @Override
    public void run() {
        rpaRunner.execute(d -> {
            //Get Game Detailed Info page by executing common (for SteamDemoTask and SteamDemoRobot) scenario
            GameDetailedInfoPage gameDetailedInfoPage = SteamDemoScenario.executeSteamDemoScenario(this.logger);

            //Get name and release date
            String gameName = gameDetailedInfoPage.getGameName();
            String gameReleaseDate = gameDetailedInfoPage.getGameReleaseDate();

            Game game = new Game();
            game.setGameName(gameName);
            game.setReleaseDate(gameReleaseDate);

            //Save data to datastore
            gameRepository.create(game);
        });
    }
}
