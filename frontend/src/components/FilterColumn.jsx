import React from "react";

function FilterColumn(props) {
  return (
    <div>
      {props.filterList.map((genre, index) => (
        <div class="mb-3 mx-4 form-check">
          <input
            type="checkbox"
            class="form-check-input"
            id={genre}
            value={genre}
            checked={props.selectedList.includes(genre)}
            onChange={(e) => props.onFilterChanged(genre, e.target.checked)}
          />
          <label class="form-check-label" for={genre}>
            {genre}
          </label>
        </div>
      ))}
    </div>
  );
}

export default FilterColumn;
