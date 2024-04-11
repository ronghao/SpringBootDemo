/**
 * 二类区，30 分钟免费(免费时间不放在 24 小时计费里面)，小/中/大型车 5元/小时 24小时内封顶 30 元，
 * 执行新能源车政策，每天首次 2 小时免费，半折优惠。
 *
 * 特殊需求周-到周五下午 3: 30 点到 6 点接送小孩，这个时间内进的车辆按照一个小时免费时间(周六和周日不需要)，
 * 其他时间进场的车都是免费 30 分钟免费，
 * @param btime
 * @param etime
 * @returns {number}
 */

function getPrice(btime, etime, isZero = 0, isSubExtraFreeDuration = false, extraFreeDuration = 0) {
    var date = new Date(btime * 1000);
    var isHitResult = isHit(date);
    var freeTime = 30;
    if (isHitResult) {
        freeTime = 60;
    }
    var second = etime - btime - freeTime * 60;
    if (second < 0) {
        return 0.0;
    }
    var minutes = second / 60 + (second % 60 > 0 ? 1 : 0);
    if (isSubExtraFreeDuration) {
        minutes = minutes - extraFreeDuration;
        if (minutes <= 0) {
            return 0.0;
        }
    } else {
        if (minutes - extraFreeDuration < 0) {
            return 0.0;
        }
    }

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
    return money / 2;
}

function calculateCost(hours) {
    var hourlyRate = 5;
    var dailyCap = 30;

    // 计算天数和剩余小时数
    var days = Math.floor(hours / 24);
    var remainingHours = hours % 24;

    // 计算费用，每天不超过封顶值
    var totalCost = (days * dailyCap) + Math.min(remainingHours * hourlyRate, dailyCap);

    return totalCost;
}


function isHit(date) {
    // 获取星期几（0表示周日，1表示周一，以此类推）
    var dayOfWeek = date.getDay();

    // 判断是否在周一到周五
    if (dayOfWeek >= 1 && dayOfWeek <= 5) {
        // 获取当前时间的小时和分钟
        var hours = date.getHours();
        var minutes = date.getMinutes();

        // 判断是否在下午3点半到6点之间
        if ((hours === 15 && minutes >= 30) || (hours > 15 && hours < 18)) {
            return true;
        }
    }

    return false;
}

//--------------------------------------

// var price = getPrice(1699018990, 1699018990 + 60 * 60);
// console.info(price)

// // 示例：判断给定时间戳是否在周一到周五的下午3点半到5点之间
var timestamp = 1702458581;//特殊
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

time = 60 * 25;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 26 + 1;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 26 + 1;
price = getPrice(timestamp, timestamp - time * 60);
console.info(time + ":" + price)
