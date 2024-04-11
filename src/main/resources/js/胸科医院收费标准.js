function getPrice(btime, etime, isZero) {

    var dayFreeTimeMin = 30
    var nightFreeTimeMin = 15;
    var day1money = 3;
    var day2money = 3;
    var night1money = 1;
    var night1TopMoney = 10;
    var dayStartTime = 9;
    var dayEndTime = 22;
    var dayTotalMoney = 75;

    var money = 0;
    var tmpTime = btime % 3600;
    btime = btime - tmpTime;
    etime = etime - tmpTime;

    var nowTimeDate = new Date(btime * 1000);
    nowTimeDate.setHours(0, 0, 0, 0);
    var btime0 = nowTimeDate.getTime() / 1000;

    nowTimeDate = new Date(etime * 1000);
    nowTimeDate.setHours(0, 0, 0, 0);
    var etime0 = nowTimeDate.getTime() / 1000;

    var btime7 = btime0 + dayStartTime * 3600;
    var btime19 = btime0 + dayEndTime * 3600;

    var etime7 = etime0 + dayStartTime * 3600;
    var etime19 = etime0 + dayEndTime * 3600;

    var day = Math.floor((etime0 - btime0) / 86400)

    if (btime < btime7) {
        if (etime - btime <= nightFreeTimeMin * 60) return 0;
    } else if (btime < btime19) {
        if (etime - btime <= dayFreeTimeMin * 60) return 0;
    } else {
        if (etime - btime <= nightFreeTimeMin * 60) return 0;
    }

    if (day === 0) {
        if (btime < btime7) {
            if (etime < btime7) {
                var tmpHour = Math.floor((etime - btime) / 3600);
                if ((etime - btime) !== tmpHour * 3600) tmpHour += 1;
                var nightMoney = tmpHour * night1money;
                if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
                money += nightMoney;
            } else if (etime < btime19) {
                var tmpHour = Math.floor((btime7 - btime) / 3600);
                if ((btime7 - btime) !== tmpHour * 3600) tmpHour += 1;
                var nightMoney = tmpHour * night1money;
                if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
                money += nightMoney;

                tmpHour = Math.floor((etime - btime7) / 1800);
                if ((etime - btime7) !== tmpHour * 1800) tmpHour += 1;
                if (tmpHour !== 0) {
                    if (tmpHour <= 2) {
                        money += day1money;
                    } else {
                        var dayMoney = day1money + (tmpHour - 2) * day2money;
                        money += dayMoney;
                    }
                }

            } else{
                var tmpHour = Math.floor((btime7 - btime) / 3600);
                if ((btime7 - btime) !== tmpHour * 3600) tmpHour += 1;
                var nightMoney = tmpHour * night1money;
                if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
                money += nightMoney
                money += dayTotalMoney
                var tmpHour = Math.floor((etime - btime19) / 3600);
                if ((etime - btime19) !== tmpHour * 3600) tmpHour += 1;
                var nightMoney = tmpHour * night1money;
                if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
                money += nightMoney
            }

        } else if (btime < btime19) {
            if (etime < btime19) {
                var tmpHour = Math.floor((etime - btime) / 1800);
                if ((etime - btime) !== tmpHour * 1800) tmpHour += 1;
                if (tmpHour !== 0) {
                    if (tmpHour <= 2) {
                        money += day1money;
                    } else {
                        var dayMoney = day1money + (tmpHour - 2) * day2money;
                        money += dayMoney;
                    }
                }
            } else {
                var tmpHour = Math.floor((btime19 - btime) / 1800);
                if ((btime19 - btime) !== tmpHour * 1800) tmpHour += 1;
                if (tmpHour !== 0) {
                    if (tmpHour <= 2) {
                        money += day1money;
                    } else {
                        var dayMoney = day1money + (tmpHour - 2) * day2money;
                        money += dayMoney;
                    }
                }

                tmpHour = Math.floor((etime - etime19) / 3600);
                if ((etime - etime19) !== tmpHour * 3600) tmpHour += 1;
                var nightMoney = tmpHour * night1money;
                if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
                money += nightMoney;
            }

        } else {
            var tmpHour = Math.floor((etime - btime) / 3600);
            if ((etime - btime) !== tmpHour * 3600) tmpHour += 1;
            var nightMoney = tmpHour * night1money;
            if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
            money += nightMoney;
        }
    } else {
        if (day === 1 && btime >= btime19) {
            if (etime - btime >= 5 * 3600) {
                money += night1TopMoney;
            } else {
                var tmpHour = Math.floor((etime - btime) / 3600);
                if ((etime - btime) !== tmpHour * 3600) tmpHour += 1;
                var nightMoney = tmpHour * night1money;
                if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
                money += nightMoney;
            }
        }
        money += (day - 1) * 43;
        if (btime >= btime7 && btime < btime19) {
            money += night1TopMoney;
            var tmpHour = Math.floor((btime19 - btime) / 1800);
            if ((btime19 - btime) !== tmpHour * 1800) tmpHour += 1;
            if (tmpHour !== 0) {
                if (tmpHour <= 2) {
                    money += day1money;
                } else {
                    var dayMoney = day1money + (tmpHour - 2) * day2money;
                    money += dayMoney;
                }
            }
        }
        if (btime < btime7) {
            money += 43;
            var tmpHour = Math.floor((btime7 - btime) / 3600);
            if ((btime7 - btime) !== tmpHour * 3600) tmpHour += 1;
            var nightMoney = tmpHour * night1money;
            if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
            money += nightMoney;
        }
        if (etime >= etime7 && etime < etime19) {
            var tmpHour = Math.floor((etime - etime7) / 1800);
            if ((etime - etime7) !== tmpHour * 1800) tmpHour += 1;
            if (tmpHour !== 0) {
                if (tmpHour <= 2) {
                    money += day1money;
                } else {
                    var dayMoney = day1money + (tmpHour - 2) * day2money;
                    money += dayMoney;
                }
            }
        }
        if (etime >= etime19) {
            money += dayTotalMoney;
            var tmpHour = Math.floor((etime - etime19) / 3600);
            if ((etime - etime19) !== tmpHour * 3600) tmpHour += 1;
            var nightMoney = tmpHour * night1money;
            if (nightMoney > night1TopMoney) nightMoney = night1TopMoney;
            money += nightMoney;
        }
    }

    return money * 1.0;
}