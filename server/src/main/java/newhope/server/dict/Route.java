package newhope.server.dict;

import java.util.Date;

public class Route {

    public static Long getTransportTypeId(String transportTypeName){
        switch (transportTypeName){
            case "bus": return 1l; //Автобус
            case "trolleybus": return 2l; //Троллейбус
            case "tramway": return 3l; //Трамвай
            case "minibus": return 4l; //Маршрутное такси
            default: return 4l;
        }
    }

    public static Date getNearestAvailableTime(Date time, Long busStopId, Long routeId){

        return null;
    }
}
