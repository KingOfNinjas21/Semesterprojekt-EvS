package at.qe.sepm.skeleton.ui.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Basic request scoped bean to test bean initialization.
 *
 * This class is part of the skeleton project provided for students of the
 * course "Softwaredevelopment and Project Management" offered by the University
 * of Innsbruck.
 */
@Component
@Scope("request")

public class ThemeBean {

	//if Theme change - change theme here
	//add theme dependency in pom.xml
	//change theme in template (main.xhtml)
    public String getApplicationTheme() {
        return "midnight";
    }
   
}