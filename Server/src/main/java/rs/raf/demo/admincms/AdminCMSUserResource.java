package rs.raf.demo.admincms;

import rs.raf.demo.entities.User;
import rs.raf.demo.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cms/users")
public class AdminCMSUserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.userService.allUsers()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User find(@PathParam("id") Integer id) {
        return this.userService.findUser(id);
    }

    @GET
    @Path("/page/{page}/{limit}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> page(@PathParam("page") Integer page, @PathParam("limit") Integer limit) {
        return this.userService.pageUsers(page, limit);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User addUser(User user) {
        return this.userService.addUser(user);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User editUser(@PathParam("id") Integer id, @Valid User user) {
        return this.userService.editUser(user);
    }

    @PUT
    @Path("/status/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User setUserStatus(@PathParam("id") Integer id) {
        return this.userService.setUserStatus(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") Integer id) {
        this.userService.deleteUser(id);
    }
}
