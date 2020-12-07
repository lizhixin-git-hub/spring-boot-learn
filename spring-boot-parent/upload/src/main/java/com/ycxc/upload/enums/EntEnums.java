package com.ycxc.upload.enums;

public class EntEnums{

	public enum EconomyKind{
		STATE(1,"110"),
		GROUP(2,"120"),
		PRIVATE(3,"170"),
		INDIVIDUAL(4,"175"),
		UNION(5,"140"),
		STOCK(6,"130"),
		FOREIGN(7,"330");

		EconomyKind(int value,String name) {
			this.value=value;
			this.name=name;
		}

		private final int value;

		private final String name;

		public int getValue() {
			return value;
		}

		public String getName() {
			return name;
		}

		public static String getDesc(int value){
			for (EconomyKind e:EconomyKind.values()) {
				if (e.getValue() == value) {
					return e.getName();
				}
			}
			return "900";
		}
	}

}
