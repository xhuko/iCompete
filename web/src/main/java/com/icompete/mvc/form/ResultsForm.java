package com.icompete.mvc.form;

import java.util.ArrayList;

/**
 * @author Sekan
 */
public class ResultsForm {
    private ArrayList<ResultForm> results;

    public ArrayList<ResultForm> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultForm> results) {
        this.results = results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultsForm that = (ResultsForm) o;

        return results != null ? results.equals(that.results) : that.results == null;
    }

    @Override
    public int hashCode() {
        return results != null ? results.hashCode() : 0;
    }
}
