package com.workfusion.examples.aa_examples_bots.steam;

import com.workfusion.examples.aa_examples_bots.steam.orm.dto.Game;
import com.workfusion.examples.aa_examples_bots.steam.task.SteamDemoTask;
import com.workfusion.odf.test.junit.IacDeveloperJUnitConfig;
import com.workfusion.odf2.junit.BotTaskFactory;
import com.workfusion.odf2.junit.OrmSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@IacDeveloperJUnitConfig
public class SteamDemoTaskTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SteamDemoTaskTest.class);

    @BeforeAll
    public static void setUp(OrmSupport ormSupport) {
        //Create uc_rce_ex_steam_demo datastore
        ormSupport.createTables(Game.class);
    }

    @Test
    @DisplayName("Should run bot task")
    void shouldRunRpaBotTask(BotTaskFactory botTaskFactory, OrmSupport ormSupport) {

        botTaskFactory.fromClass(SteamDemoTask.class).buildAndRun();

        //Log records that were put to datastore by bot task
        List<Game> recordsFromDatastore = ormSupport.getRepository(Game.class).findAll();
        recordsFromDatastore.forEach(g -> LOGGER.info(g.toString()));
    }
}
