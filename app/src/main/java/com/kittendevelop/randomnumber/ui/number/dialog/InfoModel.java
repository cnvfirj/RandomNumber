package com.kittendevelop.randomnumber.ui.number.dialog;

import com.kittendevelop.randomnumber.R;

import javax.inject.Inject;

public class InfoModel {

    private final String URL = "https://qrng.anu.edu.au/";
    private final int idReport = R.string.report_info;


    @Inject
    public InfoModel() {
    }

    public String getURL() {
        return URL;
    }

    public int getIdReport() {
        return idReport;
    }
}
