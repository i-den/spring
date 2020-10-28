package com.idenchev.javaconfig;

import com.idenchev.classes.Output;
import com.idenchev.classes.OutputAmplifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.idenchev.javaconfig"})
public class JavaCfg {

    @Bean
    public Output output() {
        return new Output();
    }

    @Bean
    public OutputAmplifier outputAmplifier() {
        Output output = output();
        OutputAmplifier outputAmplifier = new OutputAmplifier();
        outputAmplifier.setOutput(output);
        return outputAmplifier;
    }
}
