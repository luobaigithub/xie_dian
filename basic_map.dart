// @dart=2.9
// ignore_for_file: use_key_in_widget_constructors, avoid_print, sized_box_for_whitespace

import 'package:flutter/material.dart';
import 'package:flutter_bmfmap/BaiduMap/bmfmap_map.dart';
import 'package:flutter_bmfbase/BaiduMap/bmfmap_base.dart';

class BasicMap extends StatefulWidget {
  @override
  _BasicMapState createState() => _BasicMapState();
}

class _BasicMapState extends State<BasicMap> {
  Size screenSize;
  BMFMapOptions mapOptions;
  BMFMapController myMapController;

  @override
  void initState() {
    super.initState();
    mapOptions = BMFMapOptions(
        center: BMFCoordinate(22.71986,114.24771),
        zoomLevel: 14,
        mapPadding: BMFEdgeInsets(left: 30, top: 0, right: 30, bottom: 0));
  }

  // 创建完成回调
  void onBMFMapCreated(BMFMapController controller) {
    myMapController = controller;
    print("地图创建成功");

    // 地图加载回调
    myMapController?.setMapDidLoadCallback(callback: () {
      print('地图加载完成');
    });
  }

  @override
  Widget build(BuildContext context) {
    screenSize = MediaQuery.of(context).size;
    return Container(
      height: 500,
      width: 380,
      child: BMFMapWidget(
        onBMFMapCreated: (controller) {
          onBMFMapCreated(controller);
        },
        mapOptions: mapOptions,
      ),
    );
  }
}