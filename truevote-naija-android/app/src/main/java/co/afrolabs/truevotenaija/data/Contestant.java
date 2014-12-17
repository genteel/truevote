package co.afrolabs.truevotenaija.data;

import android.graphics.drawable.Drawable;

/**
 * Created by Ocheja Patrick on 13/12/2014.
 */
public class Contestant {



    private int imageId;
    private String fullName;
    private String politicalParty;

    public Contestant(int imageId, String fullName, String politicalParty) {
        this.imageId = imageId;
        this.fullName = fullName;
        this.politicalParty = politicalParty;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPoliticalParty() {
        return politicalParty;
    }

    public void setPoliticalParty(String politicalParty) {
        this.politicalParty = politicalParty;
    }

}
