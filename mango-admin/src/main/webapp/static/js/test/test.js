/**
 * Created by chenjinlong on 16/2/19.
 */
define(function(require,exports,module){
    var $ = require('jquery');

    var _ = require('underscore');

    var tpl = require('tpl/test.tpl');

    var template = _.template(tpl);

    var rs = template({name:'陈金龙'});

    console.log(rs)
});
