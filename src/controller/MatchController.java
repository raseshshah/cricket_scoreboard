package controller;

import events.BaseEvent;
import events.listener.MatchEventListener;

/**
 * design to control events for multiple matches
 * assumption is to have non parallel events for same match
 */
public interface MatchController extends MatchEventListener {
    MatchController handle(BaseEvent event);
}
