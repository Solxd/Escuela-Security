import { Component, OnInit } from '@angular/core';

import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import { CommonModule } from '@angular/common';
import {AlumnoService, Alumno} from '../../service/alumno.service';

@Component({
  selector: 'app-alumnos',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './alumnos.component.html',
  styleUrl: './alumnos.component.css'
})
export class AlumnosComponent implements OnInit {

  formulario!: FormGroup;
  alumnos: Alumno[] = [];
  mensajeError = '';
  mensajeExito = '';

  constructor(
    private fb: FormBuilder,
    private alumnoService: AlumnoService
  ) {}

  ngOnInit(): void {
    this.inicializarFormulario();
    this.obtenerAlumnos();
  }

  inicializarFormulario(): void {
    this.formulario = this.fb.group({
      nombre: ['',[Validators.required, Validators.minLength(3)]],
      apellido: ['',[Validators.required, Validators.minLength(3)]],
      dni: ['',[Validators.required, Validators.pattern('^[0-9]{7,8}$')]],
      email: ['',[Validators.required, Validators.email]]});
  }

  obtenerAlumnos(): void {
    this.alumnoService.obtenerAlumnos().subscribe({
      next: (respuesta) => {
        this.alumnos = respuesta;
      },

      error: (error) => {
        console.error(
          'Error al obtener alumnos:',
          error
        );
        this.mensajeError =
          'No se pudieron obtener los alumnos.';
      }
    });
  }
  guardarAlumno(): void {
    if (this.formulario.invalid) {
      this.formulario.markAllAsTouched();
      return;
    }
    this.mensajeError = '';
    this.mensajeExito = '';

    const nuevoAlumno: Alumno =
      this.formulario.value;

    this.alumnoService
      .crearAlumno(nuevoAlumno)
      .subscribe({

        next: (respuesta) => {

          console.log(
            'Alumno creado:',
            respuesta
          );
          this.mensajeExito ='Alumno registrado correctamente.';
          this.formulario.reset();
          this.obtenerAlumnos();

        },
        error: (error) => {
          console.error(
            'Error al crear alumno:',
            error
          );
          this.mensajeError =
            'No se pudo registrar el alumno.';
        }
      });
  }
}