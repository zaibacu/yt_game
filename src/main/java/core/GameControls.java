package core;

import java.util.*;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;

public class GameControls {
    public enum ActionTypes{
        ON_PRESS,
        ON_RELEASE
    }

    private final GameWindow window;

    private Map<Integer, List<Command>> eventsOnRelease = new HashMap<>();
    private Map<Integer, List<Command>> eventsOnPress = new HashMap<>();

    public GameControls(GameWindow window){
        this.window = window;
    }

    public void init(){
        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window.getId(), (window, key, scancode, action, mods) -> {
            if(action == GLFW_RELEASE){
                onKeyRelease(key);
            }
            else if(action == GLFW_PRESS){
                onKeyPress(key);
            }
        });

    }

    public void registerEvent(int key, ActionTypes actionType, Command cmd){
        switch(actionType){
            case ON_PRESS:
                if(!eventsOnPress.containsKey(key)){
                    eventsOnPress.put(key, new ArrayList<>());
                }

                eventsOnPress.get(key).add(cmd);

                break;
            case ON_RELEASE:
                if(!eventsOnRelease.containsKey(key)){
                    eventsOnRelease.put(key, new ArrayList<>());
                }

                eventsOnRelease.get(key).add(cmd);
                break;
        }
    }

    private void onKeyPress(int key){
        List<Command> actions = eventsOnPress.getOrDefault(key, Collections.EMPTY_LIST);
        for(Command action : actions){
            action.execute();
        }
    }

    private void onKeyRelease(int key){
        List<Command> actions = eventsOnRelease.getOrDefault(key, Collections.EMPTY_LIST);
        for(Command action : actions){
            action.execute();
        }
    }

    public void cleanup(){
        glfwFreeCallbacks(window.getId());
    }
}
