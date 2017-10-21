package tech.form3.igorg.interview.application.api.v1.resource;

import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Rest resource.
 */
@Getter
public class RestResource<T> {

    private final List<Link> links = new ArrayList<>();

    private final T data;

    public RestResource(T data) {
        this.data = data;
    }

    /**
     * Adds the given link to the resource.
     *
     * @param link the link
     */
    public void add(Link link) {
        Assert.notNull(link, "Link must not be null!");
        this.links.add(link);
    }

}
