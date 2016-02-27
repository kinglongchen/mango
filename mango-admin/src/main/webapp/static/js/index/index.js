/**
 * Created by chenjinlong on 16/2/22.
 */
define(function(require,exports,module) {
    var $ = require('jquery');

    var _ = require('underscore');

    var nav_bar_tpl = require('tpl/navbar.tpl');

    var requestUrl = require("js/common/request-url");
    $.get(requestUrl.baseInfoUrl,function(data,status) {
        if (status == 'success' && data.success) {
            console.log(data.data.appNumber);
            $("strong#appNumber").html(data.data.appNumber);
            $("strong#classNumber").html(data.data.classNumber);
            $("strong#fieldNumber").html(data.data.fieldNumber);
            $("strong#hostNumber").html(data.data.hostNumber);

        }
    });
    //$('#side-menu').append(nav_bar_tpl);

});
