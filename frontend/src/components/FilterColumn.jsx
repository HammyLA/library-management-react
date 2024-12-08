import React from "react";

function FilterColumn(props) {
  return (
    <div>
      {/* Iterate over the filterList prop to create checkboxes for each genre */}
      {props.filterList.map((genre, index) => (
        <div class="mb-3 mx-4 form-check" key={index}>
          {/* Checkbox input for each genre */}
          <input
            type="checkbox"
            class="form-check-input"
            id={genre}
            value={genre}
            checked={props.selectedList.includes(genre)}
            onChange={(e) => props.onFilterChanged(genre, e.target.checked)}
          />
          {/* Label for the checkbox */}
          <label class="form-check-label" for={genre}>
            {genre}
          </label>
        </div>
      ))}
    </div>
  );
}

export default FilterColumn;
