package ensa.ma.sensors.beans;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class  SensorItem {
        public final String id;
        public final String name;
        public final String type;
        public final String vendor;
        public final String version;
        public final String resolution;
        public final String maximumRange;
        public final String intType;
        public final String power;
        public final String minDelay;


        public SensorItem(String id, String name, String type, String vendor, String version, String resolution, String maximumRange, String intType, String power, String minDelay) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.vendor = vendor;
            this.version = version;
            this.resolution = resolution;
            this.maximumRange = maximumRange;
            this.intType = intType;
            this.power = power;
            this.minDelay = minDelay;
        }

        @Override
        public String toString() {
            return name;
        }

}
