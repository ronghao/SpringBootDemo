/**
 * 每小时 2元
 *
 * 当天17-24内进且出的免费，否则就正常计费
 * 还有24小时封顶32元
 * @param btime
 * @param etime
 * @returns {number}
 */

function getPrice(btime, etime, isZero) {
    var date = new Date(btime * 1000);
    var edate = new Date(etime * 1000);
    var isHitResult = isHit(date, edate);
    if (isHitResult) {
        return 0.0;
    }
    var freeTime = 30;
    if (etime - btime <= freeTime * 60) {
        return 0.0;
    }

    var second = etime - btime;
    var minutes = second / 60 + (second % 60 > 0 ? 1 : 0);
    var remainHours = Math.floor(minutes / 60);
    var remainMinutes = minutes % 60;
    if (remainMinutes > 0) {
        remainHours++;
    }
    var hourMoney = calculateCost(remainHours);

    var money = hourMoney;
    if (money <= 0) {
        money = 0.0;
    }
    return money;
}

function isSameDay(date1, date2) {
    return (
        date1.getFullYear() === date2.getFullYear() &&
        date1.getMonth() === date2.getMonth() &&
        date1.getDate() === date2.getDate()
    );
}

function calculateCost(hours) {
    var hourlyRate = 2;
    var dailyCap = 32;

    // 计算天数和剩余小时数
    var days = Math.floor(hours / 24);
    var remainingHours = hours % 24;

    // 计算费用，每天不超过封顶值
    var totalCost = (days * dailyCap) + Math.min(remainingHours * hourlyRate, dailyCap);

    return totalCost;
}


function isHit(date, edate) {
    // 获取星期几（0表示周日，1表示周一，以此类推）
    var dayOfWeek = date.getDay();
    // 获取当前时间的小时和分钟
    var hours = date.getHours();
    var minutes = date.getMinutes();

    var ehours = edate.getHours();
    var eminutes = edate.getMinutes();
    if (!isSameDay(date, edate)) {
        return false;
    }

    // 判断大于17点
    if (hours >= 17 && ehours >= 17) {
        return true;
    }

    return false;
}

//------------------------------------------------------------------------------------
// var price = getPrice(1699018990, 1699018990 + 60 * 60);
// console.info(price)

// // 示例：判断给定时间戳是否在周一到周五的下午3点半到5点之间
var timestamp = 1702457081;//特殊
// var timestamp = 1699018990;
// var timestamp = 1702451381;

// var date = new Date(timestamp * 1000);
// var result = isHit(date);

// console.log(result);  // 输出true或false

var time = 1;

var price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 30;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 31;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 61;
price = getPrice(timestamp, timestamp + time * 60, 0, true, 90);
console.info(time + ":" + price)


time = 100;
price = getPrice(timestamp, timestamp + time * 60, 0, false, 90);
console.info(time + ":" + price)

time = 120;
price = getPrice(timestamp, timestamp + time * 60, 0, true, 90);
console.info(time + ":" + price)

time = 121;
price = getPrice(timestamp, timestamp + time * 60, 0, false, 50);
console.info(time + ":" + price)

time = 151;
price = getPrice(timestamp, timestamp + time * 60, 0, false, 90);
console.info(time + ":" + price)

time = 60 * 10;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 23;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 25;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 26 + 1;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 26 + 1;
price = getPrice(timestamp, timestamp - time * 60);
console.info(time + ":" + price)
