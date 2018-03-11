package com.example.pc.marvelheroes.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author salim
 */

public class HeroInfo {

    @SerializedName("Name")
    private String name;
    @SerializedName("Description")
    private String description;
    @SerializedName("URL")
    private String url;
    @SerializedName("Thumbnail")
    private String thumbnail;


}