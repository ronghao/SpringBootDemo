/**
 * 免费停车时间为15分钟(含15分钟)，停放时间超过免费时间的，免费时间计入停车收费时间，以1小时为计费单位，不足1小时按1小时计费。
 * 角
 * 2、24小时为一天，超过24小时按计时收费标准重新计费。
 * 3、执行任务的军车、警车、消防车、救护车、救灾车、工程抢险车等，免收机动车停放服务费。
 * 4、连续停车超过30天以上的部按照收费标准的50%收取。
 * @param btime
 * @param etime
 * @returns {number}
 */

function getPrice(btime, etime, isZero) {
    var hourlyRate = 1;
    var dailyCap = 10;
    var freeTime = 16;

    if (etime - btime < freeTime * 60) {
        return 0.0;
    }
    var second = etime - btime;
    var minutes = Math.floor(second / 60) + (second % 60 > 0 ? 1 : 0);
    var hours = Math.floor(minutes / 60) + (minutes % 60 > 0 ? 1 : 0);
    var day = Math.floor(hours / 24);

    var money = day * dailyCap;
    var remainder = Math.floor(hours % 24);
    var remainderMoney = remainder * hourlyRate;
    remainderMoney = Math.min(remainderMoney, dailyCap);
    money += remainderMoney;

    if (hours == 720 && (minutes % 60) > 0) {
    } else if (day >= 30) {
        money = Math.floor(money / 2)
    }
    if (money <= 0) {
        money = 0.0;
    }
    return money;
}

//------------------------------------------------------------------------------------
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

time = 15;
price = getPrice(timestamp, timestamp + time * 60 + 60);
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

time = 60 * 24 * 30 - 60;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 24 * 30 + 1;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 24 * 30;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)
