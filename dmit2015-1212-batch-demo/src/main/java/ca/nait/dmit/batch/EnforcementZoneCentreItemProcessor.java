package ca.nait.dmit.batch;

import ca.nait.dmit.entity.EnforcementZoneCentre;
import jakarta.batch.api.chunk.ItemProcessor;
import jakarta.inject.Named;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.io.WKTReader;

@Named
public class EnforcementZoneCentreItemProcessor implements ItemProcessor {


    @Override
    public EnforcementZoneCentre processItem(Object item) throws Exception {

        String line = (String) item;
        final String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] tokens = line.split(delimiter, -1);    // The -1 limit allows for any number of fields and not discard trailing empty fields);

        EnforcementZoneCentre currentEnforcementZoneCentre = new EnforcementZoneCentre();
        currentEnforcementZoneCentre.setSiteId(Short.parseShort(tokens[0]));
        currentEnforcementZoneCentre.setLocationDescription(tokens[1]);
        currentEnforcementZoneCentre.setSpeedLimit(Short.parseShort(tokens[2]));
        currentEnforcementZoneCentre.setReasonCodes(tokens[3].replaceAll("[\"()]", ""));
        currentEnforcementZoneCentre.setLatitude(Double.valueOf(tokens[4]));
        currentEnforcementZoneCentre.setLongitude(Double.valueOf(tokens[5]));

        // need to add the shape, in this case POINT, to the data because we need to tell it what shape it is
        // need to remove the comma as well
        String wktText = "POINT" + tokens[6].replaceAll("[\",]","");
        // WKT == Well Known Text
        Point geoLocation = (org.locationtech.jts.geom.Point) new WKTReader().read(wktText);
        currentEnforcementZoneCentre.setGeoLocation(geoLocation);

        return currentEnforcementZoneCentre;
    }
}
