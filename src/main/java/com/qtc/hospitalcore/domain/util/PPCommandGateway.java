package com.qtc.hospitalcore.domain.util;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PPCommandGateway {

    private CommandGateway commandGateway;

    @Autowired
    public PPCommandGateway(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void sendAndWait(Object cmd) {
        Map<String, Object> metaData = new HashMap<>();
        // TODO: PP 从SecurityContextHolder.getContext().getAuthentication();中得到操作帐号id放入metaData
        metaData.put("zhangHaoId", "");

        CommandMessage commandMessage = GenericCommandMessage.asCommandMessage(cmd).withMetaData(metaData);

        commandGateway.sendAndWait(commandMessage);
    }
}
