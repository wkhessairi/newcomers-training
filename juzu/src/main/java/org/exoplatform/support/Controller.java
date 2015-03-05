package org.exoplatform.support;

import juzu.*;
import juzu.plugin.ajax.Ajax;
import juzu.template.Template;

import javax.inject.Inject;

/** @author <a href="mailto:julien.viet@exoplatform.com">Julien Viet</a> */
public class Controller {


    @Inject
    @Path("index.gtmpl")
    Template index;

    @Inject
    @Path("list.gtmpl")
    Template list;

    @View
    public void index() {
      index.render();
   }

    @Ajax
    @Resource
    public void getList() throws Exception {
        list.render();
    }
}