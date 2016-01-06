// Copyright 2015 Apcera Inc.  All Rights Reserved.

package io.nats.connector.plugin;

import org.slf4j.Logger;

/**
 * Created by colinsullivan on 12/16/15.
 */
public interface NATSConnectorPlugin {

    /**
     * Invoked when the connector is started up, before a connection
     * to the NATS cluster is made.  The NATS connection factory is
     * valid at this time, providing an opportunity to alter
     * connection parameters based on other plugin variables.
     *
     * @param logger - logger for the NATS connector process.
     * @return - true if the connector should continue, false otherwise.
     */
    public boolean OnStartup(Logger logger);

    /**
     * Invoked when the Plugin is shutting down.
     */
    public void OnShutdown();

    /**
     * Invoked when the NATS plug-in is ready to start receiving
     * and sending messages.  Subscribe and publish calls can be
     * made after this call is invoked.
     *
     * @param connector interface to the NATS connector
     *
     * @return true if the plugin can continue.
     */
    public boolean OnNatsInitialized(NATSConnector connector);

    /**
     * Invoked anytime a NATS message is received to be processed.
     * @param msg - NATS message received.
     */
    public void OnNATSMessage(io.nats.client.Message msg);

    /**
     * Invoked anytime a NATS event occurs around a connection
     * or error, alerting the plugin to take appropriate action.
     *
     * For example, when reconnecting, buffer or pause incoming
     * data to be sent to NATS.
     *
     * @param event the type of event
     * @param message a string describing the event
     */
    public void OnNATSEvent(NATSEvent event, String message);
}