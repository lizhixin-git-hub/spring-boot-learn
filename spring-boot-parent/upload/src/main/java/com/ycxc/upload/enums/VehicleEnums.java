package com.ycxc.upload.enums;

public class VehicleEnums{

	public enum OilType{
		GASOLINE('A',"汽油"),
		DIESEL_OIL('B',"柴油"),
		ELECTRIC('C',"电"),
		MIXED_OIL('D',"混合油"),
		NATURAL_GAS('E',"天然气"),
		LIQUEFIED_PETROLEUM_GAS('F',"液化石油气"),
		METHANOL('L',"甲醇"),
		ETHANOL('M',"乙醇"),
		SOLAR_ENERGY('N',"太阳能"),
        HYBRID_POWER('O',"混合动力"),
        NOTHING('Y',"无"),
        OTHER('Z',"其他");
		OilType(char value,String name) {this.value=value; this.name=name;}
		private final char value;
		private final String name;
		public char getValue() { return value; }
		public String getName() { return name; }
		public static String getDesc(char value){
			for (OilType e:OilType.values())
				if (e.getValue()==value)
					return e.getName();
			return "";
		}
		public static char getValue(String name){
			if(name!=null){
				for (OilType e:OilType.values())
					if (e.getName().contains(name))
						return e.getValue();
			}
			return 'Z';
		}
	}

	public enum AutoColor{
		WHITE('A',"白"),
		GREY('B',"灰"),
		YELLOW('C',"黄"),
		PINK('D',"粉"),
		RED('E',"红"),
		VIOLET('F',"紫"),
		GREEN('G',"绿"),
		BLUE('H',"蓝"),
		BROWN('I',"棕"),
		BLACK('J',"黑"),
		WHITE_GREEN('Y',"白绿"),
		OTHER('Z',"其他");
		AutoColor(char value,String name) {this.value=value; this.name=name;}
		private final char value;
		private final String name;
		public char getValue() { return value; }
		public String getName() { return name; }
		public static String getDesc(char value){
			for (AutoColor e:AutoColor.values())
				if (e.getValue()==value)
					return e.getName();
			return "";
		}
		public static char getValue(String name){
			if(name!=null){
				for (AutoColor e:AutoColor.values())
					if (e.getName().contains(name))
						return e.getValue();
			}
			return 'Z';
		}
	}
	
}
