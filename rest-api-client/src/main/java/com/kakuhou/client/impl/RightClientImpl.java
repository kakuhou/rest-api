package com.kakuhou.client.impl;

import java.security.PrivateKey;
import java.security.PublicKey;

import com.kakuhou.base.Rt;
import com.kakuhou.client.AbstractClient;
import com.kakuhou.client.Client;
import com.kakuhou.client.RightClient;
import com.kakuhou.rq.AddClientRq;
import com.kakuhou.rq.AddRightRq;
import com.kakuhou.rq.BindInterfaceToRightRq;
import com.kakuhou.rq.ClientBindRightRq;

public class RightClientImpl extends AbstractClient implements RightClient {
	
	@Override
	public Client initClient(String domain, PublicKey publicKey, PrivateKey privateKey, String clientId) {
		this.setClientId(clientId);
		this.setPrivateKey(privateKey);
		this.setPublicKey(publicKey);
		this.setDomain(domain);
		return this;
	}

	@Override
	public Rt<Integer> addRight(AddRightRq rq) throws Exception {
		return this.doRequest(rq);
	}

	@Override
	public Rt<Integer> bindInterfaceToRight(BindInterfaceToRightRq rq) throws Exception {
		return this.doRequest(rq);
	}

	@Override
	public Rt<Integer> addClient(AddClientRq rq) throws Exception {
		return this.doRequest(rq);
	}

	@Override
	public Rt<Integer> clientBindRight(ClientBindRightRq rq) throws Exception {
		return this.doRequest(rq);
	}

}
