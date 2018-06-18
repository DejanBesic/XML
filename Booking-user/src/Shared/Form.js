import React from 'react';

export const Form = ({ onSubmit, children, style}) =>
  <form
    style={style}
    onKeyDown={
      (e) => {
        if (e.key === 'Enter') {
          e.preventDefault();
          onSubmit();
        }
      }
    }

    onSubmit={
      (e) => {
        e.preventDefault();
        e.stopPropagation();
        onSubmit();
      }
    }>
    {children}
  </form>

export default Form;