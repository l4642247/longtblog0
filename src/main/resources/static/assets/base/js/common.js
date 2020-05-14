//加载点击top8列表
function top8(){
    $.ajax({
        url:'/article/top8',
        method:'get',
        success:function(result){
            var html = '';
            $.each(result, function(i,val){
                html += '<li><a href="/info/'+val.id+'">'+val.title+'</a></li>';
            });
            $(".tuijian ul").append(html);
        }
    });
}

//加载类别列表
function catalogInit(){
    $.ajax({
        url:'/catalog/page',
        method:'get',
        success:function(result){
            var catalogs = result.content;
            var html = '';
            $.each(catalogs, function(i,val){
                html += '<li><a href="/index.html?catalogId='+val.id+'">'+val.name+'（'+val.count+'）</a></li>';
            });
            $(".fenlei ul").append(html);
        }
    });
}

//加载所有标签
function initAllTags(){
    $.ajax({
        url: '/tag/all',
        method: 'get',
        dataType:'json',
        success: function(result){
            var html = '';
            $.each(result.content, function(i, val){
                html += '<a href="/index.html?tag=' + val.id + '">'+val.name+'</a>';
            });
            $(".cloud ul").html(html);
        }
    });
}



/*
** 时间戳显示为多少分钟前，多少天前的处理
** eg.
** console.log(dateDiff(1411111111111));  // 2014年09月19日
** console.log(dateDiff(1481111111111));  // 9月前
** console.log(dateDiff(1499911111111));  // 2月前
** console.log(dateDiff(1503211111111));  // 3周前
** console.log(dateDiff(1505283100802));  // 1分钟前
*/
var dateDiff = function (timestamp) {
    // 补全为13位
    var arrTimestamp = (timestamp + '').split('');
    for (var start = 0; start < 13; start++) {
        if (!arrTimestamp[start]) {
            arrTimestamp[start] = '0';
        }
    }
    timestamp = arrTimestamp.join('') * 1;

    var minute = 1000 * 60;
    var hour = minute * 60;
    var day = hour * 24;
    var halfamonth = day * 15;
    var month = day * 30;
    var now = new Date().getTime();
    var diffValue = now - timestamp;

    // 如果本地时间反而小于变量时间
    if (diffValue < 0) {
        return '不久前';
    }

    // 计算差异时间的量级
    var monthC = diffValue / month;
    var weekC = diffValue / (7 * day);
    var dayC = diffValue / day;
    var hourC = diffValue / hour;
    var minC = diffValue / minute;

    // 数值补0方法
    var zero = function (value) {
        if (value < 10) {
            return '0' + value;
        }
        return value;
    };

    // 使用
    if (monthC > 12) {
        // 超过1年，直接显示年月日
        return (function () {
            var date = new Date(timestamp);
            return date.getFullYear() + '年' + zero(date.getMonth() + 1) + '月' + zero(date.getDate()) + '日';
        })();
    } else if (monthC >= 1) {
        return parseInt(monthC) + "月前";
    } else if (weekC >= 1) {
        return parseInt(weekC) + "周前";
    } else if (dayC >= 1) {
        return parseInt(dayC) + "天前";
    } else if (hourC >= 1) {
        return parseInt(hourC) + "小时前";
    } else if (minC >= 1) {
        return parseInt(minC) + "分钟前";
    }
    return '刚刚';
};
