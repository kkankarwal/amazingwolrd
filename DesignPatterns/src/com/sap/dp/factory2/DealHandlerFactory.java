package com.sap.dp.factory2;

public abstract class DealHandlerFactory {
	
	private DealHandlerFactory() {
	}

	public static DealHandler getHandler(String handlerType) {
		DealHandler dealHandler = null;

		if (DealHandlerType.valueOf(handlerType) == DealHandlerType.SINGLE_DEAL) {
			dealHandler = new SingleDealHandler();
		} else if (DealHandlerType.valueOf(handlerType) == DealHandlerType.MULTI_DEAL) {
			dealHandler = new MultiDealHandler();
		}

		return dealHandler;
	}
}
