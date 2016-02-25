/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.qe.sepm.skeleton.ui.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Basic request scoped bean to test bean initialization.
 *
 * @author Michael Brunner <Michael.Brunner@uibk.ac.at>
 */
@ManagedBean
@RequestScoped
public class HelloWorldBean {

    /**
     * Returns a hello-world-string.
     *
     * @return hello-world-string
     */
    public String getHello() {
        return "Hello from a JSF-managed bean!";
    }
}
