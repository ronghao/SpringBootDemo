/**
 *
 * @param btime
 * @param etime
 * @returns {number}
 */
function getPrice(btime, etime) {
    var dayFreeTimeMin = 30;//免费时间

    var daymoney = 10;// 白天费率（7点到19点）
    var nightmoney = 100;// 夜间费率（19点到第二天7点）

    var dayStartTime = 7;//早7点
    var dayEndTime = 22;//晚10点
    return getPrice1(btime, etime, dayFreeTimeMin, daymoney, nightmoney, dayStartTime, dayEndTime);
}

function getPrice1(btime, etime, dayFreeTimeMin, daymoney, nightmoney, dayStartTime, dayEndTime) {
    var money = 0;//计费金额

    var nowTimeDate = new Date(btime * 1000);
    nowTimeDate.setHours(0, 0, 0, 0);
    var btime0 = nowTimeDate.getTime() / 1000;//开始当天0点的时间戳秒

    nowTimeDate = new Date(etime * 1000);
    nowTimeDate.setHours(0, 0, 0, 0);
    var etime0 = nowTimeDate.getTime() / 1000;//结束当天0点的时间戳秒

    var btime7 = btime0 + dayStartTime * 3600;
    var btime19 = btime0 + dayEndTime * 3600;

    var dayDur = btime19 - btime7;
    var nitghDur = 24 * 60 * 60 - dayDur;

    var etime7 = etime0 + dayStartTime * 3600;
    var etime19 = etime0 + dayEndTime * 3600;

    var day = Math.floor((etime0 - btime0) / 86400);

    if (btime < btime7) {
        if (etime - btime <= dayFreeTimeMin * 60) return 0;
    } else if (btime < btime19) {
        if (etime - btime <= dayFreeTimeMin * 60) return 0;
    } else {
        if (etime - btime <= dayFreeTimeMin * 60) return 0;
    }

    money = day * (daymoney + nightmoney);
    etime -= day * 24 * 60 * 60;
    if (btime < btime7) {
        if (etime < btime7) {
            money += nightmoney;
        } else if (etime < btime19) {
            money += nightmoney + daymoney;
        } else {
            money += nightmoney + daymoney + nightmoney;
        }
    } else if (btime < btime19) {
        if (etime < btime19) {
            money += daymoney;
        } else if (etime < btime19 + nitghDur) {
            money += daymoney + nightmoney;
        } else {
            money += daymoney + nightmoney + daymoney;
        }
    } else {
        if (etime < btime19 + nitghDur) {
            money += nightmoney;
        } else if (etime < btime19 + nitghDur + dayDur) {
            money += nightmoney + daymoney;
        } else {
            money += nightmoney + daymoney + nightmoney;
        }
    }

    return money;
}


function getPrice1(btime, etime, dayFreeTimeMin, daymoney, nightmoney, dayStartTime, dayEndTime) {
    var money = 0;
    var nowTimeDate = new Date(btime * 1000);
    nowTimeDate.setHours(0, 0, 0, 0);
    var btime0 = nowTimeDate.getTime() / 1000;
    nowTimeDate = new Date(etime * 1000);
    nowTimeDate.setHours(0, 0, 0, 0);
    var etime0 = nowTimeDate.getTime() / 1000;
    var btime7 = btime0 + dayStartTime * 3600;
    var btime19 = btime0 + dayEndTime * 3600;
    var dayDur = btime19 - btime7;
    var nitghDur = 24 * 60 * 60 - dayDur;
    var etime7 = etime0 + dayStartTime * 3600;
    var etime19 = etime0 + dayEndTime * 3600;
    var day = Math.floor((etime0 - btime0) / 86400);
    if (btime < btime7) {
        if (etime - btime <= dayFreeTimeMin * 60) return 0
    } else if (btime < btime19) {
        if (etime - btime <= dayFreeTimeMin * 60) return 0
    } else {
        if (etime - btime <= dayFreeTimeMin * 60) return 0
    }
    money = day * (daymoney + nightmoney);
    etime -= day * 24 * 60 * 60;
    if (btime < btime7) {
        if (etime < btime7) {
            money += nightmoney
        } else if (etime < btime19) {
            money += nightmoney + daymoney
        } else {
            money += nightmoney + daymoney + nightmoney
        }
    } else if (btime < btime19) {
        if (etime < btime19) {
            money += daymoney
        } else if (etime < btime19 + nitghDur) {
            money += daymoney + nightmoney
        } else {
            money += daymoney + nightmoney + daymoney
        }
    } else {
        if (etime < btime19 + nitghDur) {
            money += nightmoney
        } else if (etime < btime19 + nitghDur + dayDur) {
            money += nightmoney + daymoney
        } else {
            money += nightmoney + daymoney + nightmoney
        }
    }
    return money
}


function getPrice2(btime,etime,dayFreeTimeMin,daymoney,nightmoney,dayStartTime,dayEndTime){var money=0;var nowTimeDate=new Date(btime*1000);nowTimeDate.setHours(0,0,0,0);var btime0=nowTimeDate.getTime()/1000;nowTimeDate=new Date(etime*1000);nowTimeDate.setHours(0,0,0,0);var etime0=nowTimeDate.getTime()/1000;var btime7=btime0+dayStartTime*3600;var btime19=btime0+dayEndTime*3600;var dayDur=btime19-btime7;var nitghDur=24*60*60-dayDur;var etime7=etime0+dayStartTime*3600;var etime19=etime0+dayEndTime*3600;var day=Math.floor((etime0-btime0)/86400);if(btime<btime7){if(etime-btime<=dayFreeTimeMin*60)return 0}else if(btime<btime19){if(etime-btime<=dayFreeTimeMin*60)return 0}else{if(etime-btime<=dayFreeTimeMin*60)return 0}money=day*(daymoney+nightmoney);etime-=day*24*60*60;if(btime<btime7){if(etime<btime7){money+=nightmoney}else if(etime<btime19){money+=nightmoney+daymoney}else{money+=nightmoney+daymoney+nightmoney}}else if(btime<btime19){if(etime<btime19){money+=daymoney}else if(etime<btime19+nitghDur){money+=daymoney+nightmoney}else{money+=daymoney+nightmoney+daymoney}}else{if(etime<btime19+nitghDur){money+=nightmoney}else if(etime<btime19+nitghDur+dayDur){money+=nightmoney+daymoney}else{money+=nightmoney+daymoney+nightmoney}}return money}


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

console.info("入场时间:" + new Date(timestamp * 1000))

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
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)


time = 100;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 120;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 121;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 151;
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 5;
price = getPrice(timestamp, timestamp + time * 60);
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
price = getPrice(timestamp, timestamp + time * 60);
console.info(time + ":" + price)

time = 60 * 26 + 1;
price = getPrice(timestamp, timestamp - time * 60);
console.info("-" + time + ":" + price)


price = getPrice2(1703865600, 1703876400, 30, 10, 100, 7, 10)

console.info("---" + time + ":" + price)
