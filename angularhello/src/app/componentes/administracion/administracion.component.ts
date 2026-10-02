import { Component, OnInit } from '@angular/core';

import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from '@angular/forms';

import { CommonModule } from '@angular/common';

import {
  AdministracionService,
  Administracion
} from '../../service/administracion.service';

@Component({
  selector: 'app-administracion',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './administracion.component.html',
  styleUrl: './administracion.component.css'
})
export class AdministracionComponent implements OnInit {

  formulario!: FormGroup;

  administracion: Administracion[] = [];

  cargos: string[] = [
    'DIRECTOR',
    'VICEDIRECTOR',
    'SECRETARIO',
    'PRECEPTOR',
    'DOCENTE',
    'ADMINISTRATIVO'
  ];

  mensajeError = '';

  mensajeExito = '';

  constructor(
    private fb: FormBuilder,
    private administracionService: AdministracionService
  ) {}

  ngOnInit(): void {

    this.inicializarFormulario();

    this.obtenerAdministracion();

  }

  inicializarFormulario(): void {

    this.formulario = this.fb.group({

      nombre: [
        '',
        [
          Validators.required,
          Validators.minLength(3)
        ]
      ],

      apellido: [
        '',
        [
          Validators.required,
          Validators.minLength(3)
        ]
      ],

      email: [
        '',
        [
          Validators.required,
          Validators.email
        ]
      ],

      cargo: [
        '',
        [
          Validators.required
        ]
      ]

    });

  }

  obtenerAdministracion(): void {

    this.administracionService
      .obtenerAdministracion()
      .subscribe({

        next: (respuesta) => {

          this.administracion = respuesta;

        },

        error: (error) => {

          console.error(
            'Error al obtener personal:',
            error
          );

          this.mensajeError =
            'No se pudo obtener el personal.';

        }

      });

  }

  guardarAdministracion(): void {

    if (this.formulario.invalid) {

      this.formulario.markAllAsTouched();

      return;

    }

    this.mensajeError = '';

    this.mensajeExito = '';

    const nuevoPersonal: Administracion =
      this.formulario.value;

    this.administracionService
      .crearAdministracion(nuevoPersonal)
      .subscribe({

        next: (respuesta) => {

          console.log(
            'Personal creado:',
            respuesta
          );

          this.mensajeExito =
            'Personal registrado correctamente.';

          this.formulario.reset();

          this.obtenerAdministracion();

        },

        error: (error) => {

          console.error(
            'Error al crear personal:',
            error
          );

          this.mensajeError =
            'No se pudo registrar el personal.';

        }

      });

  }

}