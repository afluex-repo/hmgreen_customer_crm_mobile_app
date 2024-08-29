package com.hm.greencity.customermanagement.models.Gallery;
import com.google.gson.annotations.SerializedName;


public class PhototsModel {
    @SerializedName("Image")
    public int Image;

    public PhototsModel(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
