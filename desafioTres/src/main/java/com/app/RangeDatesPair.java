package com.app;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hector
 */
public class RangeDatesPair {
        private final Date in;
        private final Date fin;
        public RangeDatesPair(Date in,Date fin){this.in=in;this.fin=fin;}
        public Date getIn() {
            return in;
        }
        public Date getFin() {
            return fin;
        }


    }