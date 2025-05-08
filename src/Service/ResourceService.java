package Service;

import entity.Resource;
import java.util.List;


import java.util.*;

public class ResourceService {
    private Map<String, Resource> resources = new HashMap<>();

    public Resource addResource(String name, String type, double costPerHour) {
        Resource resource = new Resource(name, type, costPerHour);
        resources.put(resource.getId(), resource);
        return resource;
    }

    public List<Resource> getAllResources() {
        return new ArrayList<>(resources.values());
    }

    public Resource getResourceById(String id) {
        return resources.get(id);
    }

    public void updateResource(Resource resource, String name, String type, double cost) {
        resource.setName(name);
        resource.setType(type);
        resource.setCostPerHour(cost);
    }

    public void deleteResource(String id) {
        resources.remove(id);
    }
}