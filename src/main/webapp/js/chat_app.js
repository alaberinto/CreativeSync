//var userID = ${userid};
$(document).ready(function(){
    /***Contact List Define & Event handler**/
    $("#mycontacts>.mdl-list").selectable({
        selected:function(event, ui){
            //alert($(this).attr("opponent-id"));
            //alert($(ui).attr("class"));
            console.log(ui);
            //console.log(ui.attr("opponent-id"));
            var opponent_id = $(this).find('.ui-selectee.ui-selected').attr('opponent-id');
            activeOpponentId = opponent_id;
            //$("#active_contact>span:first-child").replaceWith(ui.selected.innerHTML);            
            $("#active_contact>span:first-child").replaceWith($(ui.selected).find("span.mdl-list__item-primary-content").clone());
            $("#dialog-field").load(location.href+"?action=privatechat&key=refresh&opponent_id="+opponent_id, function( response, status, xhr ) {
                $('#dialog-field').scrollTop($('#dialog-field')[0].scrollHeight);
              });
        }
    });
    
    /*****Message InputBox Event Handler*****/
    $("#messagefield").on("keyup", function(e){
        if(e.keyCode == 13) {
            sendChatMessage();
        }
    });
    
    /** Contact Search Key Event***/
  $("#searchfield").on("keyup", function(e) {
      //e.preventExtensions()
        if(e.keyCode == 13) {
            // Do something
            var value = $(this).val().toLowerCase();
            if(value.length>0){
                //$('#searched_contacts').toggle();
                /*$('#mycontacts').toggle();
                $('#searched_contacts').load("Messenger?action=getonlineuser&userid="+currentUserId+"&word="+value+" #searched_contacts>ul", function(responseTxt, statusTxt, xhr){
                    $('#searched_contacts').removeClass("hide");
                });*/
                /*$.ajax({
                    type: "GET",
                    url: "Messenger?action=getonlineuser&userid="+currentUserId,
                    //url:"${pageContext.request.contextPath}/Messenger?action=getonlineuser&userid="+currentUserId,
                    dataType:'json',
                    success: function(data){
                        alert(data.message);
                        console.log(data.message);
                    },
                    failure:function(){
                        alert("ddd");
                    }
                });*/
            }
        }else{
            var value = $(this).val().toLowerCase();
            $("#mycontacts ul li").filter(function() {
              $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        }
  });
  
  $("#send-button").click(function(){
      
  });
  
  setupRefresh();
  resizeMiddle();
  $(window).resize(resizeMiddle);
});
function sendChatMessage(){
    var msg = $("#messagefield").val();
    //console.log(msg);
    $("#dialog-field").load(encodeURI(location.href+"?action=privatechat&key=sendmsg&opponent_id="+activeOpponentId+"&data="+msg), function( response, status, xhr ) {
                $('#dialog-field').scrollTop($('#dialog-field')[0].scrollHeight);
              });
    $("#messagefield").val("");
}

function setupRefresh()
{
    setInterval(refreshBlock, 1000);
    refreshBlock();
}

function refreshBlock()
{
    //$('#messages').load(location.href + " #messages");
    if(activeOpponentId!=-1){
        $("#dialog-field").load(location.href+"?action=privatechat&key=refresh&opponent_id="+activeOpponentId);
    }    
}

var minHeight = 30;
var resizeMiddle = function() {
    var h = $('body').height() - $('.main-panel .header').height() - $('.enter-message').height() - $("nav").height()-40;
    h = h > minHeight ? h : minHeight;
    $('#dialog-field').height(h);    
}
