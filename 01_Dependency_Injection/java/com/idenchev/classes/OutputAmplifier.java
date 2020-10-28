package com.idenchev.classes;

public class OutputAmplifier {
    Output output;

    public String output() {
        return "OutputAmplifier: " + output.output().toUpperCase();
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public Output getOutput() {
        return output;
    }
}
