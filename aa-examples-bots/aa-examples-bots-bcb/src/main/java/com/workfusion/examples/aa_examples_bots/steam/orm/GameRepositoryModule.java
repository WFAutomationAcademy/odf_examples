package com.workfusion.examples.aa_examples_bots.steam.orm;

import com.j256.ormlite.support.ConnectionSource;
import com.workfusion.odf2.core.cdi.OdfModule;
import org.codejargon.feather.Provides;

import javax.inject.Singleton;
import java.sql.SQLException;

public class GameRepositoryModule implements OdfModule {

    @Provides
    @Singleton
    public GameRepository gameRepository(ConnectionSource connectionSource) throws SQLException {
        return new GameRepository(connectionSource);
    }
}
