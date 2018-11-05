package com.cgwx.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class LayerInfo {


    @Value("${geoserverPath}")
    private String geoserverPath;
    @Value("${geoserverUsername}")
    private String geoserverUsername;
    @Value("${geoserverPassword}")
    private String geoserverPassword;
    @Value("${thumbnailPath}")
    private String thumbnailPath;
    @Value("${thumbnailPath1}")
    private String thumbnailPath1;

    public String getThumbnailPath1() {
        return thumbnailPath1;
    }

    public void setThumbnailPath1(String thumbnailPath1) {
        this.thumbnailPath1 = thumbnailPath1;
    }

    public String getThumbnailPath2() {
        return thumbnailPath2;
    }

    public void setThumbnailPath2(String thumbnailPath2) {
        this.thumbnailPath2 = thumbnailPath2;
    }

    @Value("${thumbnailPath2}")
    private String thumbnailPath2;
    public String getGeoserverPath() {
        return geoserverPath;
    }

    public String getGeoserverUsername() {
        return geoserverUsername;
    }

    public String getGeoserverPassword() {
        return geoserverPassword;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    @Value("${realSavePathLocal}")
    private String realSavePathLocal;
    public String getRealSavePathLocal() {
        return realSavePathLocal;
    }

    @Value("${canBeVisitedSavePath}")
    private String canBeVisitedSavePath;
    public String getCanBeVisitedSavePath() {
        return canBeVisitedSavePath;
    }
    @Value("${standardProductFileSavePath}")
    private String standardProductFileSavePath;
    public String getStandardProductFileSavePath() {
        return standardProductFileSavePath;
    }
    @Value("${advancedProductFileSavePath}")
    private String advancedProductFileSavePath;
    public String getAdvancedProductFileSavePath() {
        return advancedProductFileSavePath;
    }
    @Value("${thematicProductFileSavePath}")
    private String thematicProductFileSavePath;
    public String getThematicProductFileSavePath() {
        return thematicProductFileSavePath;
    }
    @Value("${dataBaseProductFileSavePath}")
    private String dataBaseProductFileSavePath;
    public String getDataBaseProductFileSavePath() {
        return dataBaseProductFileSavePath;
    }

    @Value("${standardServicePath}")
    private String standardServicePath;
    public String getStandardServicePath() {
        return standardServicePath;
    }

    @Value("${pointsUploadFileSavePath}")
    private String pointsUploadFileSavePath;
    public String getPointsUploadFileSavePath() {
        return pointsUploadFileSavePath;
    }
}
