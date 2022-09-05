package com.workfusion.examples.aa_examples_bots.steam.orm.dto;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.workfusion.odf2.core.orm.Datastore;
import com.workfusion.odf2.core.orm.DatastoreType;

import java.util.UUID;

@DatabaseTable(tableName = "steam_demo")
@Datastore(type = DatastoreType.VERSIONED)
public class Game {

    @DatabaseField(columnName = "record_id", dataType = DataType.UUID, generatedId = true)
    private UUID recordId;

    @DatabaseField(columnName = "game_name")
    private String gameName;

    @DatabaseField(columnName = "release_date")
    private String releaseDate;

    public Game() {
    }

    public String getRecordId() {
        return recordId.toString();
    }

    public void setRecordId(String recordId) {
        this.recordId = UUID.fromString(recordId);
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("recordId=").append(recordId);
        sb.append(", gameName='").append(gameName).append('\'');
        sb.append(", releaseDate='").append(releaseDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
