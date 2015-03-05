
function createTaskTable(){
   $.getJSON('/rest/demo/listtasks?user=root', function(list) {
          if (list.tasks.length <1){
                             $(".projectList").html("<div style='text-align: center;'>No tasks</div>");

                         }else{

                             $.each(list.tasks, function(i, a){

                                 var link = "";


                                 //link += "<li class='project clearfix' style='margin-left=10%;' id='"+a.task+"'>"

                                 link += "<div class='detail' > <div class='name'> <a style='margin-left=10%;' href='#' onclick='return false'>"+a.task+"</a> </div>";

                                 //link += "<div class='peopleConnection' style='margin-left=10%;'>Project Lead : "+a.task+"  </div></li>";

                                 $("#projects").append(link);
 });
                }


$('.jz').on("click", '.getlist', function(){
                            $('#content').jzLoad(
                                "Controller.getList()");
                        });


            });







}
$('.jz').on("click", '.getlist', function(){

                               var projectname = "root";//$('#inputEmail5').val();
                               var response = '';
                                               // make a POST ajax call
                                               $.ajax({
                                                   type: "GET",
                                                   url: "/rest/demo/listtasks", // set your URL here
                                                   data: {
                                                   user: projectname, // send along this data (can add more data separated by comma)

                                               },
                                               success : function(text)
                                                        {
                                                            response = text;
                                                        },

                                               }).done(function() {

                                               });
                                               //alert(response);

                        });
$(document).ready(function () {
$("#taskManagement").show();
createTaskTable();

});

var re = /^(.*)\(\)$/;
$.fn.jz = function() {
    return this.closest(".jz");
};
$.fn.jzURL = function(mid) {
    return this.
        jz().
        children().
        filter(function() { return $(this).data("method-id") == mid; }).
        map(function() { return $(this).data("url"); })[0];
};
$.fn.jzLoad = function(url, data, complete) {
    var match = re.exec(url);
    if (match != null) {
        var repl = this.jzURL(match[1]);
        url = repl || url;
    }
    if (typeof data === "function") {
        complete = data;
        data = null;
    }
    return this.load(url, data, complete);
};
