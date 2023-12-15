document.addEventListener('DOMContentLoaded', function () {
            var navLinks = document.querySelectorAll('#opciones-nav .nav-link');

            const alertPlaceholder = document.getElementById('liveAlertPlaceholder');

            const appendAlert = (message, type) => {
                const wrapper = document.createElement('div');
                wrapper.innerHTML = [
                    `<div class="card-footer">`,
                    `<div class="alert alert-primary alert-dismissible" role="alert">`,
                    `   <div>${message}</div>`,
                    '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
                    '</div>'
                ].join('');

                alertPlaceholder.append(wrapper);
            };

            navLinks.forEach(function (link) {
                link.addEventListener('click', function (event) {

                    var message = 'Se estan cargando los datos solicitados, el proceso puede tardar un poco...';
                    var type = 'success';

                    appendAlert(message, type);
                });
            });
        });