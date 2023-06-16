package com.autobots.automanager.models;

import com.autobots.automanager.controllers.SellController;
import com.autobots.automanager.entities.Sell;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddLinkSell implements AddLink<Sell> {
	@Override
	public void linkAdd (List<Sell> list) {
		for (Sell sells : list) {
			Link linkSell = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(SellController.class)
							.findSellById(sells.getId()))
					.withSelfRel();
			sells.add(linkSell);
		}
	}
	
	@Override
	public void linkAdd (Sell sell) {
		Link linkSell = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(SellController.class)
						.findAllSells())
				.withRel("Vendas");
		sell.add(linkSell);
	}

}
