/**
 * Created by chenjinlong on 16/2/26.
 */
define(function (require, exports, module) {
    var $ = require('jquery');

    var _ = require('underscore');

    var app_unit_tpl = require('tpl/index/app-unit.tpl');
    var template = _.template(app_unit_tpl);

    $.get(requestUrl.baseInfoUrl, function (data, status) {
        if (status == 'success' && data.success) {
            var appInfoList = data.data;
            var lineHtml = "<div>\n";
            for (index in appInfoList) {
                if (index % 4 == 0) {
                    lineHtml += "</div>\n";
                    $.("body").append(lineHtml);
                    lineHtml = "<div>\n";
                }
                appInfo = appInfoList[index];
                appNameHtml = template(appInfo);
                lineHtml += appNameHtml + "\n";


            }
            $.("body").append("</div>");
        }
    });


})
