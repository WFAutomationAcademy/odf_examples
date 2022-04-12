package com.workfusion.examples.aa_examples_bots.steam.orm;

import com.j256.ormlite.support.ConnectionSource;
import com.workfusion.examples.aa_examples_bots.steam.orm.dto.Game;
import com.workfusion.odf2.core.orm.repository.OrmLiteRepository;

import java.sql.SQLException;

public class GameRepository extends OrmLiteRepository<Game> {

    public GameRepository(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Game.class);
    }
}
