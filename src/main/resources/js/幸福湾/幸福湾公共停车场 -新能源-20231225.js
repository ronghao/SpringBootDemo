function getPrice(btime, etime, isZero, isSubExtraFreeDuration , extraFreeDuration) {
    if (isUndef(isZero)) isZero = 0;
    if (isUndef(isSubExtraFreeDuration)) isSubExtraFreeDuration = false;
    if (isUndef(extraFreeDuration)) extraFreeDuration = 0;

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

function isUndef(val) {
    return val === undefined || val === null || val === ''
}
