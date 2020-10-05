package org.lunasphere.hn.models.internal;

import java.sql.Timestamp;

public class DatabaseChange {
    Long changeId;
    int metaId, objectId;
    ChangeObjectType changeType;
    Timestamp lastChange, lastBuild;

    public static enum ChangeObjectType {
        UNKNOWN,
        EXTENSION,
        MUSIC,
        MISSION,
        NODE,
        FACTION,
        THEME,
        ACTION_SET;

        public static ChangeObjectType fromInt(int value) {
            switch (value) {
                case 1:
                    return EXTENSION;
                case 2:
                    return MUSIC;
                case 3:
                    return MISSION;
                case 4:
                    return NODE;
                case 5:
                    return FACTION;
                case 6:
                    return THEME;
                case 7:
                    return ACTION_SET;
                default:
                    return UNKNOWN;
            }
        }
    };

    public DatabaseChange(Long changeId, int metaId, int objectId, ChangeObjectType changeType, Timestamp lastChange, Timestamp lastBuild) {
        this.changeId = changeId;
        this.metaId = metaId;
        this.objectId = objectId;
        this.changeType = changeType;
        this.lastChange = lastChange;
        this.lastBuild = lastBuild;
    }

    public Long getChangeId() {
        return changeId;
    }

    public void setChangeId(Long changeId) {
        this.changeId = changeId;
    }

    public int getMetaId() {
        return metaId;
    }

    public void setMetaId(int metaId) {
        this.metaId = metaId;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public ChangeObjectType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeObjectType changeType) {
        this.changeType = changeType;
    }

    public Timestamp getLastChange() {
        return lastChange;
    }

    public void setLastChange(Timestamp lastChange) {
        this.lastChange = lastChange;
    }

    public Timestamp getLastBuild() {
        return lastBuild;
    }

    public void setLastBuild(Timestamp lastBuild) {
        this.lastBuild = lastBuild;
    }
}
