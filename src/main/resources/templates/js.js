<script>
    fetch("/api/users").then((response) => {
    return response.json();
})
    .then((users) => {
    const tableBody = document.getElementById("userTableBody"); // Reference to the tbody of the table
    users.forEach((user) => {
    const row = document.createElement("tr");

    const idCell = document.createElement("td");
    idCell.textContent = user.id;
    row.appendChild(idCell);

    const nameCell = document.createElement("td");
    nameCell.textContent = user.username; // Assuming `username` exists in the user object
    row.appendChild(nameCell);

    const ageCell = document.createElement("td");
    ageCell.textContent = user.age;
    row.appendChild(ageCell);

    const rolesCell = document.createElement("td");
    rolesCell.textContent = user.roles.map(role => role.roleDesignation).join(", ");
    row.appendChild(rolesCell);

    // Create Edit User Button
    const editButtonCell = document.createElement("td");
    editButtonCell.innerHTML = `
                <button type="button" class="btn btn-info text-white" data-bs-toggle="modal"
                    data-bs-target="#editUserModal${user.id}">
                    Edit User
                </button>
            `;
    row.appendChild(editButtonCell);

    // Create Delete User Button
    const deleteButtonCell = document.createElement("td");
    deleteButtonCell.innerHTML = `
                <button type="button" class="btn btn-danger text-white" data-bs-toggle="modal"
                    data-bs-target="#deleteUserModal${user.id}">
                    Delete User
                </button>
            `;
    row.appendChild(deleteButtonCell);

    tableBody.appendChild(row); // Add the row to the table body

    // Create the Edit User Modal
    const editModal = document.createElement("div");
    editModal.classList.add("modal", "fade");
    editModal.id = `editUserModal${user.id}`;
    editModal.setAttribute("tabindex", "-1");
    editModal.setAttribute("aria-labelledby", `editUserModalLabel${user.id}`);
    editModal.setAttribute("aria-hidden", "true");
    editModal.innerHTML = `
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editUserModalLabel${user.id}">Edit User</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/api/user" method="POST" id="editUserForm${user.id}">
                                <div class="form-group">
                                    <label for="username${user.id}">Username</label>
                                    <input type="text" class="form-control" id="username${user.id}" name="username" value="${user.username}">
                                </div>
                                <div class="form-group">
                                    <label for="age${user.id}">Age</label>
                                    <input type="number" class="form-control" id="age${user.id}" name="age" value="${user.age}">
                                </div>
                                <div class="form-group">
                                    <label for="roles${user.id}">Roles</label>
                                    <input type="text" class="form-control" id="roles${user.id}" name="roles" value="${user.roles.map(role => role.roleDesignation).join(', ')}">
                                </div>
                                <button type="submit" class="btn btn-info">Save Changes</button>
                            </form>
                        </div>
                    </div>
                </div>
            `;
    document.body.appendChild(editModal); // Append the modal to the body

    // Create the Delete User Modal
    const deleteModal = document.createElement("div");
    deleteModal.classList.add("modal", "fade");
    deleteModal.id = `deleteUserModal${user.id}`;
    deleteModal.setAttribute("tabindex", "-1");
    deleteModal.setAttribute("aria-labelledby", `deleteUserModalLabel${user.id}`);
    deleteModal.setAttribute("aria-hidden", "true");
    deleteModal.innerHTML = `
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="deleteUserModalLabel${user.id}">Delete User</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <p>Are you sure you want to delete the user <strong>${user.username}</strong>?</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="button" class="btn btn-danger" onclick="deleteUser(${user.id})">Delete</button>
                        </div>
                    </div>
                </div>
            `;
    document.body.appendChild(deleteModal); // Append the delete modal to the body
});
})
    .catch((error) => {
    console.error("Error fetching users:", error); // Handle any errors
});

    // Function to delete user
    function deleteUser(userId) {
    fetch(`/api/users/${userId}`, {
        method: 'DELETE',
    })
        .then(response => {
            if (response.ok) {
                alert('User deleted successfully');
                location.reload(); // Reload page to reflect changes
            } else {
                alert('Failed to delete user');
            }
        })
        .catch((error) => {
            console.error("Error deleting user:", error);
        });
}
</script>