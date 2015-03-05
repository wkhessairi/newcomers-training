@Application
@Portlet(name="TestPortlet")
@juzu.plugin.asset.Assets(
        scripts = {
                @juzu.plugin.asset.Script(id = "jquery",src = "jquery-1.9.1.min.js")
                //@juzu.plugin.asset.Script(id="dataTable",src="jquery.dataTables.min.js")
        }
        //,
        //stylesheets = {
        //@Stylesheet(src ="jquery.dataTables.min.css", location = AssetLocation.APPLICATION)
//}
)

//@Assets("*")
package org.exoplatform.support;

import juzu.Application;
import juzu.plugin.portlet.Portlet;

//@juzu.Application package org.sample;