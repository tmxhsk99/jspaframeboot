//HTML DOM Load 이후 실행 start
$(function () {
    let trainBox = $('.realtime_train_info_box');
    let train_dep = $('.train_dep');
    let train_bu = $('.train_bu');
    let train_su = $('.train_su');
    let train_K = $('.train_K');
    let train_M = $('.train_M');

    // WebSocket 로직 Start
    // handshake
    let sock = new SockJS("/dashboard-data");
    // 1. SockJS를 내부에 들고 있는 client를 내어준다.
    let client = Stomp.over(sock);

    // 2. connection이 맺어지면 실행된다.
    client.connect({}, function () {
        // 3. subscribe(path, callback)로 메시지를 받을 수 있다. callback 첫번째 파라미터의 body로 메시지의 내용이 들어온다.
        client.subscribe('/subscribe/train',function(train){
            let realTimeTrainReq = JSON.parse(train.body);
            let vehicleType = realTimeTrainReq.vehicleType;
            let vehicleMap = realTimeTrainReq.vehicleInfoMap;

            if(vehicleType === "dep"){
                //화면 리셋
                train_dep.empty();
                Object.keys(vehicleMap).forEach(function(k){
                    train_dep.append('<li> VehicleInfo [' + vehicleType +']'+
                        ' lineNo : '+ vehicleMap[k].lineNo +
                        ' pvid : '+ vehicleMap[k].pvid +
                        ' receivedTime : '+ vehicleMap[k].receivedTime +
                        ' stationOid : '+ vehicleMap[k].stationOid +
                        ' useYn : '+ vehicleMap[k].useYn +
                        ' vechileid : '+ vehicleMap[k].vechileid +
                        ' intPvid : '+ vehicleMap[k].intPvid +
                        '</li>');
                });
            }
        })
    })

    let htmlDataBinder = (function() {
        
    }());
});
//HTML DOM Load 이후 실행 end