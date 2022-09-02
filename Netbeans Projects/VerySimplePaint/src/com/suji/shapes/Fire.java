package com.suji.shapes;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.geom.*;
import java.util.List;
import just.MShape;
import just.ShapesLoader;
import just.Sprite;




public class Fire extends Fire_ShapeLoader {
	// Write your code here.

}




class Fire_ShapeLoader extends Sprite implements ShapesLoader{

	public Fire_ShapeLoader() {
		loadShapes(this);
	}

	@Override
	public void initShapes(List<MShape> shapes) {
		Path2D s1 = new Path2D.Double();
			s1.moveTo(200.0, 300.0);
			s1.lineTo(310.0, 250.0);
			s1.lineTo(340.0, 260.0);
			s1.lineTo(450.0, 270.0);
			s1.lineTo(420.0, 350.0);
			s1.lineTo(570.0, 310.0);
			s1.lineTo(520.0, 420.0);
			s1.lineTo(430.0, 510.0);
			s1.lineTo(310.0, 570.0);
			s1.lineTo(200.0, 520.0);
			s1.lineTo(250.0, 490.0);
			s1.lineTo(20.0, 570.0);
			s1.lineTo(130.0, 450.0);
			s1.lineTo(100.0, 420.0);
			s1.lineTo(120.0, 380.0);
			s1.lineTo(180.0, 410.0);
			s1.lineTo(140.0, 260.0);
			s1.lineTo(150.0, 260.0);
			s1.lineTo(200.0, 300.0);
			s1.closePath();
		shapes.add(new MShape(s1,new Color(255,0,0) ));

		Path2D s3 = new Path2D.Double();
			s3.moveTo(110.0, 542.1738891601562);
			s3.lineTo(202.63157653808594, 504.34783935546875);
			s3.lineTo(260.52630615234375, 491.7391357421875);
			s3.lineTo(272.1052551269531, 516.95654296875);
			s3.lineTo(272.1052551269531, 554.7825927734375);
			s3.lineTo(306.84210205078125, 580.0);
			s3.lineTo(457.368408203125, 479.13043212890625);
			s3.lineTo(468.9473571777344, 453.9130554199219);
			s3.lineTo(538.4210815429688, 378.2608642578125);
			s3.lineTo(550.0, 365.65216064453125);
			s3.lineTo(480.52630615234375, 403.478271484375);
			s3.lineTo(434.2105407714844, 428.6956481933594);
			s3.lineTo(387.8947448730469, 416.0869445800781);
			s3.lineTo(399.47369384765625, 353.0434875488281);
			s3.lineTo(411.0526428222656, 315.2173767089844);
			s3.lineTo(411.0526428222656, 290.0);
			s3.lineTo(295.2631530761719, 290.0);
			s3.lineTo(272.1052551269531, 290.0);
			s3.lineTo(191.05262756347656, 340.4347839355469);
			s3.lineTo(156.3157958984375, 340.4347839355469);
			s3.lineTo(179.4736785888672, 403.478271484375);
			s3.lineTo(179.4736785888672, 428.6956481933594);
			s3.lineTo(202.63157653808594, 441.3043518066406);
			s3.closePath();
		shapes.add(new MShape(s3,new Color(255,102,0) ));

		Path2D s2 = new Path2D.Double();
			s2.moveTo(150.0, 517.3912963867188);
			s2.lineTo(223.6842041015625, 484.7826232910156);
			s2.lineTo(269.7368469238281, 473.9130554199219);
			s2.lineTo(278.9473571777344, 495.65216064453125);
			s2.lineTo(278.9473571777344, 528.2608642578125);
			s2.lineTo(306.5789489746094, 550.0);
			s2.lineTo(426.3157958984375, 463.0434875488281);
			s2.lineTo(435.52630615234375, 441.3043518066406);
			s2.lineTo(490.7894592285156, 376.0869445800781);
			s2.lineTo(500.0, 365.2173767089844);
			s2.lineTo(444.7368469238281, 397.8260803222656);
			s2.lineTo(407.8947448730469, 419.5652160644531);
			s2.lineTo(371.0526428222656, 408.6956481933594);
			s2.lineTo(380.2631530761719, 354.34783935546875);
			s2.lineTo(389.47369384765625, 321.7391357421875);
			s2.lineTo(389.47369384765625, 300.0);
			s2.lineTo(297.368408203125, 300.0);
			s2.lineTo(278.9473571777344, 300.0);
			s2.lineTo(214.4736785888672, 343.478271484375);
			s2.lineTo(186.84210205078125, 343.478271484375);
			s2.lineTo(205.26315307617188, 397.8260803222656);
			s2.lineTo(205.26315307617188, 419.5652160644531);
			s2.lineTo(223.6842041015625, 430.4347839355469);
			s2.closePath();
		shapes.add(new MShape(s2,new Color(255,255,0) ));

	}
}