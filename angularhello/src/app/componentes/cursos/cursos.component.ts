import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { CommonModule } from '@angular/common';
import {CursoService,Curso} from '../../service/curso.service';

@Component({
  selector: 'app-cursos',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.css'
})
export class CursosComponent implements OnInit {

  formulario!: FormGroup;
  cursos: Curso[] = [];
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private fb: FormBuilder,
    private cursoService: CursoService
  ) {}

  ngOnInit(): void {
    this.inicializarFormulario();
    this.obtenerCursos();
  }

  inicializarFormulario(): void {
    this.formulario = this.fb.group({
      ciclo_lectivo: [
        '',
        [
          Validators.required
        ]
      ],

      division: [
        '',
        [
          Validators.required
        ]
      ],
      grado: [
        '',
        [
          Validators.required
        ]
      ],
      turno: [
        '',
        [
          Validators.required
        ]
      ],
      cupo_maximo: [
        '',
        [
          Validators.required
        ]
      ]
    });
  }

  obtenerCursos(): void {
    this.cursoService.obtenerCursos().subscribe({
      next: (respuesta) => {
        this.cursos = respuesta;
      },

      error: (error) => {
        console.error(
          'Error al obtener cursos:',
          error
        );
        this.mensajeError =
          'No se pudieron obtener los cursos.';
      }
    });
  }

  guardarCurso(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }

    this.mensajeError = '';
    this.mensajeExito = '';
    const nuevoCurso: Curso = this.formulario.value;
    this.cursoService
      .crearCurso(nuevoCurso)
      .subscribe({

        next: (respuesta) => {
          console.log(
            'Curso creado:',
            respuesta
          );
          this.mensajeExito =
            'Curso registrado correctamente.';
          this.formulario.reset();
          this.obtenerCursos();
        },

        error: (error) => {
          console.error(
            'Error al crear curso:',
            error
          );
          this.mensajeError =
            'No se pudo registrar el curso.';
        }
      });

  }

}